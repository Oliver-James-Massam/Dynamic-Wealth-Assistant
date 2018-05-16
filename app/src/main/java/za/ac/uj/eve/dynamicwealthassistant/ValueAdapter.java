package za.ac.uj.eve.dynamicwealthassistant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.ViewHolder> {

    private List<Value> values;

    public ValueAdapter(List<Value> values)
    {
        this.values = values;
    }

    @Override
    public ValueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.value_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ValueAdapter.ViewHolder holder, int position) {
        holder.valueAmount.setText(values.get(position).getAmount()+"");
        holder.valueType.setText(values.get(position).getValueType());
        holder.valueTransactionDate.setText(values.get(position).getTransactionDate()+"");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView valueAmount;
        public TextView valueType;
        public TextView valueTransactionDate;

        public ViewHolder(View itemView) {
            super(itemView);
            valueAmount = itemView.findViewById(R.id.valueAmount);
            valueType = itemView.findViewById(R.id.valueType);
            valueTransactionDate = itemView.findViewById(R.id.valueTransactionDate);
        }
    }
}
